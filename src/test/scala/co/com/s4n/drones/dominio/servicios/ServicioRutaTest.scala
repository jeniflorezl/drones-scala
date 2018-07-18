package co.com.s4n.drones.dominio.servicios

import org.scalatest.FunSuite

class ServicioRutaTest extends FunSuite{

  test("test preparar rutas"){
    val entregas = List("AAIID", "AIDIII", "DDIIAAA")
    ServicioRutaObj.prepararRutas(entregas)
    val list = List(1,2,3)
    val result = for {
      l <- list
      r = List(l)
    } yield r
   println(result)
  }
}
