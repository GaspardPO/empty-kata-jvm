open class Neibourhood {
    open fun update(cell: Cell) : Cell {
        return DeadCell()
    }

}

class StableNeigbourhood : Neibourhood() {

    override fun update(cell: Cell) : Cell {
        return cell
    }
}

class Reproduction : Neibourhood() {
    override fun update(cell: Cell) : Cell {
        return LiveCell()
    }
}

class Underpopulation : Neibourhood() {

}

class OverPopulation : Neibourhood() {

}



