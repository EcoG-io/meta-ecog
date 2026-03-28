SUMMARY = "Core utilities for Python packages"
HOMEPAGE = "https://github.com/pypa/packaging"
LICENSE = "MIT & Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=faadaedca9251a90b205c9167578ce91"

inherit pypi python_flit_core

SRC_URI[sha256sum] = "d443872c98d677bf60f6a1f2f8c1cb748e8fe762d2bf9d3148b5599295b0fc4f"

# a circular dependency: python_flit_core -> python3-build -> python3-packaging
