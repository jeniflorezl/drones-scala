package co.com.s4n.drones.dominio.servicios

import java.io.{BufferedWriter, File, FileWriter, PrintWriter}

import scala.io.Source
import scala.util.Try

object OperandoArchivos {
  def leerArchivo = {
    val filename: String = "./src/resources/in.txt"
    Try {
      val lines = Source.fromFile(filename).getLines.toList
      lines
    }
  }

  def escribirArchivo(message: String, file2: String) = {
    val file = new File("./src/resources/out" + file2 + ".txt")
    val bw = new BufferedWriter(new FileWriter(file, true))
    bw.write(message)
    bw.newLine()
    bw.close()
  }
}
