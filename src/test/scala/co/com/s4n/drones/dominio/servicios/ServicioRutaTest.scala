package co.com.s4n.drones.dominio.servicios

import co.com.s4n.drones.dominio.vo.{A, D, I}
import org.scalatest.FunSuite
import org.scalatest.Matchers._

class ServicioRutaTest extends FunSuite{

  test("test preparar rutas"){
    val entregas = OperandoArchivos.leerArchivo
    if (entregas.isSuccess){
      val rutas = ServicioRutaObj.prepararRutas(entregas.get)
      rutas should be(List(List(A(), A(), A(), A(), I(), A(), A(),
        D()), List(D(), D(), A(), I(), A(), D()), List(A(), A(), I(), A(), D(), A(), D())))
    }else "Error"

  }

  test("listas"){
    val list = List(List(1,2,3,5,4), List(3,4,5,2,3), List(1,2,3,5,4),List(1,2,3,5,4),
      List(1,2,3,5,4))
    val list2 = list.grouped(3)
    println(list2)
  }
}
