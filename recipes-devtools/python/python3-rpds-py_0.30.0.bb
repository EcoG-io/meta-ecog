SUMMARY = "Python bindings to the Rust rpds crate for persistent data structures"
HOMEPAGE = "https://pypi.org/project/rpds-py/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7767fa537c4596c54141f32882c4a984"

PYPI_PACKAGE = "rpds_py"

inherit pypi python_maturin cargo-update-recipe-crates

require ${BPN}-crates.inc

SRC_URI[sha256sum] = "dd8ff7cf90014af0c0f787eea34794ebf6415242ee1d6fa91eaba725cc441e84"

# Cargo.lock version 4 requires Cargo >=1.78; Yocto ships 1.75. Downgrade to
# v3 — the file content is structurally identical for this package.
do_configure:prepend() {
    sed -i 's/^version = 4$/version = 3/' ${S}/Cargo.lock
}

# rpds-1.2.0 and archery-1.2.2 use edition2024 (requires Cargo >=1.82).
# Yocto ships Cargo 1.75 — downgrade to edition2021 which is ABI-compatible
# for the features used by these crates.
do_compile:prepend() {
    # Yocto ships Cargo/Rust 1.75. Strip edition2024 and rust-version MSRV
    # guards from all vendored crates so they build with the available toolchain.
    find ${WORKDIR}/cargo_home/bitbake -name "Cargo.toml" | while read toml; do
        sed -i 's/^edition = "2024"$/edition = "2021"/' "${toml}"
        sed -i '/^rust-version/d' "${toml}"
    done

    # ptr::addr_eq stabilized in Rust 1.76; ptr::eq is equivalent for thin pointers.
    sed -i 's/ptr::addr_eq/ptr::eq/g' \
        ${WORKDIR}/cargo_home/bitbake/triomphe-0.1.15/src/arc.rs

    # ptr::from_ref / ptr::from_mut stabilized in Rust 1.76; replace with
    # equivalent raw pointer casts which are stable since Rust 1.0.
    for f in ${WORKDIR}/cargo_home/bitbake/archery-1.2.2/src/shared_pointer/kind/arc/mod.rs \
              ${WORKDIR}/cargo_home/bitbake/archery-1.2.2/src/shared_pointer/kind/arct/mod.rs \
              ${WORKDIR}/cargo_home/bitbake/archery-1.2.2/src/shared_pointer/kind/rc/mod.rs; do
        [ -f "$f" ] || continue
        sed -i \
            -e 's|ptr::from_ref::<UntypedArc>(self\.inner\.deref())|(self.inner.deref() as *const UntypedArc)|g' \
            -e 's|ptr::from_mut::<UntypedArc>(self\.inner\.deref_mut())|(self.inner.deref_mut() as *mut UntypedArc)|g' \
            -e 's|ptr::from_ref::<UntypedRc>(self\.inner\.deref())|(self.inner.deref() as *const UntypedRc)|g' \
            -e 's|ptr::from_mut::<UntypedRc>(self\.inner\.deref_mut())|(self.inner.deref_mut() as *mut UntypedRc)|g' \
            "$f"
    done
}

