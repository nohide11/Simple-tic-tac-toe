val X3 = mutableListOf<Char>('X', 'X', 'X')
val O3 = mutableListOf<Char>('O', 'O', 'O')
val BXWIN = mutableListOf<Boolean>()
val BOWIN = mutableListOf<Boolean>()

fun numCheckRight(myList: MutableList<MutableList<Char>>, a: Char) {
    // Print check true
    var schet: Int = 0
    while (schet == 0) {
        print("Enter the coordinates: ")
        var coord: String = readLine().toString()
        var wordsList: List<String> = coord.split(" ")

        while (wordsList.size != 2 || wordsList[0].toIntOrNull() == null
            && wordsList[1].toIntOrNull() == null) {
            println("You should enter numbers!")
            print("Enter the coordinates: ")
            coord = readLine().toString()
            wordsList = coord.split(" ")
        }
        if (wordsList[0].toIntOrNull() == null && wordsList[1].toIntOrNull() == null) {
            println("You should enter numbers!")
            continue
        } else if (wordsList[0].toIntOrNull() !in 1 .. 3 || wordsList[1].toIntOrNull() !in 1..3) {
            println("Coordinates should be from 1 to 3!")
            continue
        } else if (myList[wordsList[0].toInt() - 1][wordsList[1].toInt() - 1] != ' ') {
            println("This cell is occupied! Choose another one!")
            continue
        } else {
            schet++
            myList[wordsList[0].toInt() - 1][wordsList[1].toInt() - 1] = a
            printSet(myList)
        }
    }

}

fun printSet(myList: MutableList<MutableList<Char>>) {
    println("---------")
    for (i in 0 until 3) {
        val j = 0
        println("| ${myList[i][j]} ${myList[i][j + 1]} ${myList[i][j + 2]} |")
    }
    println("---------")
}

fun winOrLoseOrImpossibleOrDraw(list: MutableList<MutableList<Char>>) {
    // Winners
    for (i in 0 until 3) {
        if (list[i] == X3) BXWIN.add(true) else if (list[i] == O3) BOWIN.add(true)
        if (list[0][i] == 'O' && list[1][i] == 'O' && list[2][i] == 'O') BOWIN.add(true)
        if (list[0][i] == 'X' && list[1][i] == 'X' && list[2][i] == 'X') BXWIN.add(true)
    }

    val check = mutableListOf<Char>()
    for (i in 0 until 3) {
        check.add(list[i][i])
    }
    if (check == X3) BXWIN.add(true) else if (check == O3) BOWIN.add(true)

    check.clear()

    for (i in 0 until 3) {
        check.add(list[i][2 - i])
    }
    if (check == X3) BXWIN.add(true) else if (check == O3) BOWIN.add(true)

    //quantity
    var quantityX: Int = 0
    var quantityO: Int = 0
    for (i in 0 until 3) {
        for (j in 0 until 3) {
            if (list[i][j] == 'X') ++quantityX
            if (list[i][j] == 'O') ++quantityO
        }
    }
    var s = 0
    if (quantityX > quantityO) {
        s = quantityX - quantityO
    } else if (quantityO > quantityX) {
        s = quantityO - quantityX
    }


    //test not finished
    if (s >= 2) {
        println("Impossible")
    }
    else if (quantityX + quantityO != 9 && BXWIN.isEmpty() && BOWIN.isEmpty())

    //check Draw or Impossible or win
    if (BXWIN.isEmpty() && BOWIN.isEmpty() && quantityX + quantityO == 9) println("Draw")

    if (BXWIN.isNotEmpty() && BOWIN.isNotEmpty()) {
        println("Impossible")
    }
    else if (BXWIN.isNotEmpty()) {
        println("X wins")
    }
    else if (BOWIN.isNotEmpty()) {
        println("O wins")
    } else if (quantityX + quantityO == 9) println("Draw")
}

fun main() {
    val list = mutableListOf(
        mutableListOf<Char>(' ', ' ', ' '),
        mutableListOf<Char>(' ', ' ', ' '),
        mutableListOf<Char>(' ', ' ', ' '),
    )
    println("---------")
    for (i in 0 .. 8 step 3) {
        println("| ${" "} ${" "} ${" "} |")
    }
    println("---------")

    loop@for (i in 0 until 9) {
        if (i % 2 == 0) {
            numCheckRight(list, 'X')
            winOrLoseOrImpossibleOrDraw(list)
            when {
                BXWIN.isNotEmpty() -> break@loop
            }
        } else {
            numCheckRight(list, 'O')
            winOrLoseOrImpossibleOrDraw(list)
            when {
                BOWIN.isNotEmpty() -> break@loop
            }
        }
    }

}