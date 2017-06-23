inherit image_types

# Create an image that can by written onto a SD card using dd.
# Originally written for rasberrypi and rewritten for allwinner based boards.
# Source: meta-sunxi/classes/sdcard_image-sunxi.bbclass
#
# The disk layout used is:
#    0              -> 8*1024                           - reserverd
#    8*1024         -> 2048*1024                        - U-Boot with SPL
#    2048*1024      -> 2048*1024 + IMAGE_ROOTFS_SIZE    - rootfs
#
# Linux and dtb are expected to be in rootfs (e.g. in /boot)
#
# There is external dependency on variable IMAGE_ROOTFS_SIZE.

# Begin of the first partition [KiB]
IMAGE_ROOTFS_ALIGNMENT = "2048"

# SD card image name
SDIMG = "${IMGDEPLOYDIR}/${IMAGE_NAME}.rootfs.a711-sdimg"

SDIMG_ROOTFS_TYPE ?= "ext4"
SDIMG_ROOTFS = "${IMGDEPLOYDIR}/${IMAGE_NAME}.rootfs.${SDIMG_ROOTFS_TYPE}"

# This image depends on the rootfs image
IMAGE_TYPEDEP_a711-sdimg = "${SDIMG_ROOTFS_TYPE}"

IMAGE_DEPENDS_a711-sdimg += "\
    coreutils-native \
    parted-native \
    virtual/bootloader:do_deploy \
"

IMAGE_CMD_a711-sdimg () {
    # Initialize sdcard image file
    dd if=/dev/zero of=${SDIMG} bs=1K count=$(expr ${IMAGE_ROOTFS_ALIGNMENT} + ${IMAGE_ROOTFS_SIZE})

    # Create partition table and the partition itself
    parted -s ${SDIMG} mklabel msdos
    parted -s ${SDIMG} unit KiB mkpart primary ext4 ${IMAGE_ROOTFS_ALIGNMENT} 100%
    parted ${SDIMG} print

    # Copu U-Boot with SPL
    dd if=${DEPLOY_DIR_IMAGE}/u-boot-sunxi-with-spl.bin of=${SDIMG} bs=1024 seek=8 conv=notrunc

    # Copy partition
    dd if=${SDIMG_ROOTFS} of=${SDIMG} conv=notrunc seek=1 bs=${IMAGE_ROOTFS_ALIGNMENT}K

    sync
}
