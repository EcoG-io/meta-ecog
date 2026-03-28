SUMMARY = "Backported and Experimental Type Hints for Python 3"
HOMEPAGE = "https://github.com/python/typing_extensions"
LICENSE = "PSF-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fcf6b249c2641540219a727f35d8d2c2"

PYPI_PACKAGE = "typing_extensions"

inherit pypi python_flit_core

SRC_URI[sha256sum] = "0cea48d173cc12fa28ecabc3b837ea3cf6f38c6d1136f85cbaaf598984861466"

# typing-extensions 4.15.0 uses PEP 639 license string; flit_core 3.9.0 expects dict form
do_configure:prepend() {
    sed -i 's|^license = "\(.*\)"|license = {text = "\1"}|' ${S}/pyproject.toml
    sed -i '/^license-files/d' ${S}/pyproject.toml
}

# No BBCLASSEXTEND: 4.15.0 requires flit_core>=3.11 but native has 3.9.0.
# OE-Core's python3-typing-extensions_4.12.2 (with BBCLASSEXTEND) provides the native variant.

