SUMMARY = "Extensions to the standard Python datetime module"
HOMEPAGE = "https://dateutil.readthedocs.io/"
LICENSE = "Apache-2.0 & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3155c7bdc71f66e02678411d2abf996"

# PyPI distributes this as 2.9.0.post0; set SRC_URI explicitly so the
# pypi class does not override S with the base version directory name.
SRC_URI = "https://files.pythonhosted.org/packages/source/p/python-dateutil/python-dateutil-2.9.0.post0.tar.gz"
SRC_URI[sha256sum] = "37dd54208da7e1cd875388217d5e00ebd4179249f90fb72437e91a35459a0ad3"

S = "${WORKDIR}/python-dateutil-2.9.0.post0"

inherit setuptools3

do_compile:prepend() {
    # setup.py uses use_scm_version={...} and setup.cfg declares
    # setup_requires = setuptools_scm — both cause setuptools to invoke pip
    # at build time, which is unavailable in the Yocto sandbox.
    # Patch setup.py to use a static version and remove setup_requires from setup.cfg.
    python3 -c "
import re
path = '${S}/setup.py'
content = open(path).read()
content = re.sub(r'use_scm_version=\{[^}]+\},', \"version='2.9.0.post0',\", content)
open(path, 'w').write(content)
"
    sed -i '/^setup_requires/d' ${S}/setup.cfg
}

RDEPENDS:${PN} += "python3-six"
