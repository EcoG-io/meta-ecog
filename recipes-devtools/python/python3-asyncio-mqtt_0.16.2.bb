SUMMARY = "Idiomatic asyncio MQTT client"
HOMEPAGE = "https://github.com/sbtinstruments/asyncio-mqtt"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a462083fa4d830bdcf8c22a8ddf453cf"

# No v0.16.2 tag exists; pinned to HEAD of main which corresponds to this release
# Note: package was renamed from asyncio-mqtt to aiomqtt at this version
SRC_URI = "git://github.com/sbtinstruments/asyncio-mqtt.git;protocol=https;branch=main"
SRCREV = "c8f8248029dc1cad635bf0c6cb8df6227b0be1db"

S = "${WORKDIR}/git"

inherit python_hatchling

RDEPENDS:${PN} += "python3-paho-mqtt python3-asyncio"

