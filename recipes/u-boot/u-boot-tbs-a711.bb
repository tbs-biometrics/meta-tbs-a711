require recipes-bsp/u-boot/u-boot.inc

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

DEPENDS += "dtc-native"

PV = "v2017.05-tbs-a711+git${SRCPV}"

SRC_URI = "git://github.com/tbs-biometrics/u-boot-a711.git;branch=tbs-a711-v2017.05"
SRCREV = "7ec44a624d645ca44f73b40cbdf4bf2cbdee388f"

S = "${WORKDIR}/git"

SPL_BINARY = "u-boot-sunxi-with-spl.bin"

COMPATIBLE_MACHINE = "(a711)"
