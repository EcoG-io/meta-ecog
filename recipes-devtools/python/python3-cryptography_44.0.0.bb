SUMMARY = "Provides cryptographic recipes and primitives to python developers"
HOMEPAGE = "https://cryptography.io/"
LICENSE = "Apache-2.0 & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8c3617db4fb6fae01f1d253ab91511e4 \
                    file://LICENSE.APACHE;md5=4e168cce331e5c827d4c2b68a6200e1b \
                    file://LICENSE.BSD;md5=5ae30ba4123bc4f2fa49aa0b0dce887b"

PYPI_PACKAGE = "cryptography"

inherit pypi python_maturin cargo-update-recipe-crates

require ${BPN}-crates.inc

SRC_URI[sha256sum] = "cd4e834f340b4293430701e772ec543b0fbe6c2dea510a5286fe0acabe153a02"

DEPENDS += "python3-cffi-native pkgconfig-native openssl"

# Yocto ships Cargo/Rust 1.75. Strip edition2024 and rust-version MSRV guards
# from all vendored crates so they build with the available toolchain.
do_compile:prepend() {
    find ${WORKDIR}/cargo_home/bitbake -name "Cargo.toml" | while read toml; do
        sed -i 's/^edition = "2024"$/edition = "2021"/' "${toml}"
        sed -i '/^rust-version/d' "${toml}"
    done
}

RDEPENDS:${PN} += " \
    python3-cffi \
    python3-numbers \
    python3-threading \
"

