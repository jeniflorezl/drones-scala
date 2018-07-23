package co.com.s4n.drones.dominio.servicios

import org.scalatest.FunSuite
import org.scalatest.Matchers._

import scala.util.Success

class ServicioArchivosTest extends FunSuite{
  test("Probando leer archivo"){
    val list = OperandoArchivos.leerArchivo
    list should be(Success(List("AAAAIAAD", "DDAIAD", "AAIADAD")))
  }

  test("Probando escribir en un archivo"){
    OperandoArchivos.escribirArchivo("hola", "1")
    OperandoArchivos.escribirArchivo("hola2", "1")
  }

}
