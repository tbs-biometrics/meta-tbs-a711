SUMMARY = "Reference image for testing of TBS A711 tablet"

IMAGE_ROOTFS_SIZE = "524288"

# Basic stuff
IMAGE_INSTALL = "\
    packagegroup-base \
    packagegroup-core-boot \
"

# Some utilities and tools
IMAGE_INSTALL = "\
    bash \
    cpufrequtils \
    evtest \
    fb-test \
    htop \
    lsof \
    memtester \
    openssh-scp \
    openssh-ssh \
    openssh-sshd \
    rsync \
    screen \
    strace \
    vim \
"

IMAGE_LINGUAS = ""

inherit core-image
