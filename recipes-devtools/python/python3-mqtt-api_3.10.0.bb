SUMMARY = "Python implementation of JOSEV's MQTT API"
HOMEPAGE = "https://github.com/SwitchEV/tree/main/legacy"
LICENSE = "CLOSED"

SRC_URI = "file://mqtt_api-3.10.0.tar.gz"
SRC_URI[sha256sum] = "125c3f7bacf7703b576b070b5a5bd1996f35c9ec97276e8c41d00ebaabd54a88"

S = "${WORKDIR}/mqtt_api-3.10.0"

inherit python_poetry_core

RDEPENDS:${PN} += " \
    python3-jsonschema \
    python3-paho-mqtt \
    python3-asyncio-mqtt \
    python3-docutils \
"
