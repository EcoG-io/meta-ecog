SUMMARY = "OCPP Service"
LICENSE = "CLOSED"

SRC_URI = "git://github.com/EcoG-io/ocpppy.git;protocol=https;branch=master"
SRC_URI += "file://ocpp-service.service"

SRCREV = "4d06aa365302ed825c1cfa7a871b09a978684771"

S = "${WORKDIR}/git"

inherit systemd

SYSTEMD_SERVICE:${PN} = "ocpp-service.service"

# All runtime dependencies — built by individual recipes in recipes-devtools/python/
RDEPENDS:${PN} += " \
    python3-core \
    python3-asyncio-mqtt \
    python3-attrs \
    python3-cffi \
    python3-cryptography \
    python3-dataclasses-json \
    python3-docutils \
    python3-environs \
    python3-greenlet \
    python3-jsonschema \
    python3-jsonschema-specifications \
    python3-marshmallow \
    python3-mqtt-api \
    python3-mypy-extensions \
    python3-ocpp \
    python3-packaging \
    python3-paho-mqtt \
    python3-pycparser \
    python3-pyopenssl \
    python3-python-dateutil \
    python3-python-dotenv \
    python3-referencing \
    python3-rpds-py \
    python3-six \
    python3-sqlalchemy \
    python3-typing-extensions \
    python3-typing-inspect \
    python3-websockets \
    bash \
"

do_install() {
    install -d ${D}/opt/ocpppy/cs
    cp -r ${S}/cs/* ${D}/opt/ocpppy/cs/

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/ocpp-service.service ${D}${systemd_system_unitdir}
}

FILES:${PN} += " \
    /opt/ocpppy/cs \
    ${systemd_system_unitdir}/ocpp-service.service \
"
