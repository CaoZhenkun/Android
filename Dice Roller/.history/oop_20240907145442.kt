class SmartDevice {
    var speakerVolume = 2
        set(value) {
            if (value in 0..100) {
                field = value
            }
        }
}

fun main() {
    val smartTvDevice = SmartDevice()
}