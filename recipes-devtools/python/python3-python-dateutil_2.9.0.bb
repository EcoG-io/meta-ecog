SUMMARY = "Extensions to the standard Python datetime module"
HOMEPAGE = "https://dateutil.readthedocs.io/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=51d9729f70c1b29a5c8a9cf866b32456"

PYPI_PACKAGE = "python-dateutil"
# PyPI distributes this as 2.9.0.post0; override the archive name so the
# pypi class fetches the correct tarball (PV stays 2.9.0 for BitBake).
PYPI_ARCHIVE_NAME = "python-dateutil-2.9.0.post0.tar.gz"

inherit pypi setuptools3

SRC_URI[sha256sum] = "37dd54208da7e1cd875388217d5e00ebd4179249f90fb72437e91a35459a0ad3"

RDEPENDS:${PN} += "python3-six"
