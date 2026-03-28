SUMMARY = "Extensions to the standard Python datetime module"
HOMEPAGE = "https://dateutil.readthedocs.io/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=51d9729f70c1b29a5c8a9cf866b32456"

# PyPI distributes this as 2.9.0.post0; set SRC_URI explicitly so the
# pypi class does not override S with the base version directory name.
SRC_URI = "https://files.pythonhosted.org/packages/source/p/python-dateutil/python-dateutil-2.9.0.post0.tar.gz"
SRC_URI[sha256sum] = "37dd54208da7e1cd875388217d5e00ebd4179249f90fb72437e91a35459a0ad3"

S = "${WORKDIR}/python-dateutil-2.9.0.post0"

inherit setuptools3

RDEPENDS:${PN} += "python3-six"
