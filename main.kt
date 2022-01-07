data class Car(var plate: String?, var color: String?)

class ParkingLot(val size: Int) {
    val lot: MutableList<Car> = mutableListOf()
    init {
        for (i in 0 until size) {
            lot.add(Car(null, null))
        }
        println("Created a parking lot with $size spots.")
    }
    fun addCar(newCar: Car) {
        var isCarAdded = false

        for ((index, car) in lot.withIndex()) {
            if (car.plate == null) {
                car.plate = newCar.plate
                car.color = newCar.color

                println("${car.color} car parked in spot ${index + 1}.")
                isCarAdded = true
                break
            }
        }

        if (!isCarAdded) {
            println("Sorry, the parking lot is full.")
        }
    }
    fun removeCar(place: Int) {
        if (lot[place - 1].plate == null) {
            println("Spot $place is already free.")
        } else {
            lot[place - 1] = Car(null, null)
            println("Spot $place is free.")
        }
    }
    fun showStatus() {
        var isLotHasCars = false
        for ((index, car) in lot.withIndex()) {
            if (car.plate != null) {
                println("${index + 1} ${car.plate} ${car.color}")
                isLotHasCars = true
            }
        }
        if (!isLotHasCars) {
            println("Parking lot is empty.")
        }
    }
    fun spotByColor(color: String) {
        val spottedCars = mutableListOf<Int>()
        for ((index, car) in lot.withIndex()) {
            if (car.color!!.toLowerCase() == color.toLowerCase()) {
                spottedCars.add(index + 1)
            }
        }
        if (spottedCars.isEmpty()) {
            println("No cars with color $color were found.")
        } else {
            println(spottedCars.joinToString())
        }
    }
    fun regByColor(color: String) {
        val spottedCars = mutableListOf<String>()
        for ((index, car) in lot.withIndex()) {
            if (car.color.equals(color, ignoreCase = true)) {
                spottedCars.add(car.plate!!)
            }
        }
        if (spottedCars.isEmpty()) {
            println("No cars with color $color were found.")
        } else {
            println(spottedCars.joinToString())
        }
    }
    fun spotByReg(reg: String) {
        var isCarFound = false
        for ((index, car) in lot.withIndex()) {
            if (car.plate == reg) {
                println(index + 1)
                isCarFound = true
                break
            }
        }
        if (!isCarFound) {
            println("No cars with registration number $reg were found.")
        }
    }
    companion object {
        fun noLotMsg() {
            println("Sorry, a parking lot has not been created.")
        }
    }
}

fun main() {
    var parkingLot: ParkingLot? = null

    loop@while (true) {
        val task = readLine()!!.split(" ")
        when (task[0]) {
            "create" -> parkingLot = ParkingLot(task[1].toInt())
            "park" -> parkingLot?.addCar(Car(task[1], task[2])) ?: ParkingLot.noLotMsg()
            "leave" -> parkingLot?.removeCar(task[1].toInt()) ?: ParkingLot.noLotMsg()
            "status" -> parkingLot?.showStatus() ?: ParkingLot.noLotMsg()
            "spot_by_color" -> parkingLot?.spotByColor(task[1]) ?: ParkingLot.noLotMsg()
            "reg_by_color" -> parkingLot?.regByColor(task[1]) ?: ParkingLot.noLotMsg()
            "spot_by_reg" -> parkingLot?.spotByReg(task[1]) ?: ParkingLot.noLotMsg()
            "exit" -> break@loop
        }
    }
}

