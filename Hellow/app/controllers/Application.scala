package controllers

import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._

class Application extends Controller {

  def index = Action {
   Ok(views.html.test("test"))
  }
val form = Form( "name" -> text )
val form2 = Form( "pass" -> text )
  def formSample = Action { implicit request =>
    val name = form.bindFromRequest.get
    val pass = form2.bindFromRequest.get
    Ok(name+pass)
  }

}
