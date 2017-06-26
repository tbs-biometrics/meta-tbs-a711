require recipes-kernel/linux/linux.inc
require recipes-kernel/linux/linux-dtb.inc

TBS_KERNEL_VERSION = "4.12-rc7"

PV = "${TBS_KERNEL_VERSION}+git${SRCPV}"

SRC_URI = "\
    git://github.com/tbs-biometrics/linux-a711.git;branch=v${TBS_KERNEL_VERSION}-tbs-a711 \
"
SRCREV = "143d91456db0ef9506708ffb629b58f2b2dbf13c"

S = "${WORKDIR}/git"

do_configure_prepend() {
    bbnote "Copying defconfig"
    cp ${S}/arch/${ARCH}/configs/sunxi_defconfig ${WORKDIR}/defconfig
}

KERNEL_DEVICETREE = "sun8i-a83t-tbs-a711.dtb"

COMPATIBLE_MACHINE = "(a711)"
