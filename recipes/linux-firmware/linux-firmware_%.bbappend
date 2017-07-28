LICENSE_${PN}-bcm43362 = "Firmware-broadcom_bcm43xx"
FILES_${PN}-bcm43362 = "\
  ${nonarch_base_libdir}/firmware/brcm/brcmfmac43362-sdio.bin \
"
RDEPENDS_${PN}-bcm43362 += "${PN}-broadcom-license"
PACKAGES =+ "${PN}-bcm43362"
