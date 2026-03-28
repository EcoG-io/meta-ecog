SUMMARY = "MQTT version 3.1/3.1.1 client library"
HOMEPAGE = "https://eclipse.org/paho"
LICENSE = "EPL-1.0 | EDL-1.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=8e5f264c6988aec56808a3a11e77b913"

PYPI_PACKAGE = "paho-mqtt"

inherit pypi setuptools3

SRC_URI[sha256sum] = "2a8291c81623aec00372b5a85558a372c747cbca8e9934dfe218638b8eefc26f"

RDEPENDS:${PN} += " \
    python3-io \
    python3-logging \
    python3-math \
    python3-netclient \
    python3-threading \
"

