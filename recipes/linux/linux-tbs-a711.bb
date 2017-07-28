require recipes-kernel/linux/linux.inc
require recipes-kernel/linux/linux-dtb.inc

TBS_KERNEL_VERSION = "4.12-rc7+13-rc1"

PV = "${TBS_KERNEL_VERSION}+git${SRCPV}"

SRC_URI = "\
    git://github.com/tbs-biometrics/linux-a711.git;branch=tbs/4.13 \
"
SRCREV = "a5bbfcb2a2bf608dde295d84b41973ce5eda7c35"

S = "${WORKDIR}/git"

do_configure_prepend() {
    bbnote "Copying defconfig"
    cp ${S}/arch/${ARCH}/configs/tbs_a711_defconfig ${WORKDIR}/defconfig
}

KERNEL_DEVICETREE = "sun8i-a83t-tbs-a711.dtb"

COMPATIBLE_MACHINE = "(a711)"
