package tp1.grailsgroovy

import grails.converters.JSON
import grails.converters.XML

class ApiController {

    def annonce() {
        switch(request.getMethod()) {
            case "GET":
                if (!params.id)
                    return response.status = 400
                def annonceInstance = Annonce.get(params.id)
                if (!annonceInstance)
                    return response.status = 404
                response.withFormat {
                    xml { render annonceInstance as XML}
                    json { render annonceInstance as JSON }
                }
                break

            case "PUT":
                /*if (!request.JSON || !params.id || !request.JSON.containsKey("title") || !request.JSON.containsKey("description") || !request.JSON.containsKey("validTill"))
                    return response.status = 400
                def annonceParams = request.JSON
                def annonceInstance = Annonce.get(params.id)
                if (!annonceInstance) {return response.status = 404}
                else
                    annonceInstance.properties.each { key ->
                      if (!annonceParams.containsKey("key"))
                          annonceInstance.properties.key = null
                      else
                          annonceInstance.properties.key = annonceParams["key"]}
                    annonceInstance.properties.save(flush:true)
                    return response.status = 200*/
                break
            case "PATCH":
                if (!request.JSON || !params.id)
                    return response.status = 400
                def annonceParams = request.JSON
                def annonceInstance = Annonce.get(params.id)
                if (!annonceInstance || (annonceParams.author && !User.get(annonceParams.author.id)))
                    return response.status = 404
                else
                    annonceInstance.properties= annonceParams; annonceInstance.save(flush:true); return response.status = 200
                break
            case "DELETE":
                if (!params.id)
                    return response.status = 400
                def annonceInstance = Annonce.get(params.id)
                if (!annonceInstance)
                    return response.status = 404
                else
                    annonceInstance.delete(flush:true);return response.status = 200
                response.withFormat {
                    xml { render annonceInstance as XML}
                    json { render annonceInstance as JSON }
                }
                break
            default:
                return response.status = 405
                break
        }
        return response.status = 406
    }

    def annonces() {
        switch(request.getMethod()) {
            case "GET":
                def annonceInstance = Annonce.list()
                if (!annonceInstance)
                    return response.status = 404
                response.withFormat {
                    xml { render annonceInstance as XML}
                    json { render annonceInstance as JSON }
                }
                break
            case "POST":
                def annonceParams= request.JSON
                if (annonceParams.author && !User.get(annonceParams.author.id)){
                    return response.status = 404
                }
                def annonceInstance = new Annonce(annonceParams)
                if(annonceInstance.save(flush:true)){
                    response.status = 201
                    response.withFormat {
                        xml { render annonceInstance as XML}
                        json { render annonceInstance as JSON }
                    }
                } else {
                    return response.status= 400
                }
                break
            default:
                return response.status = 405
                break
        }
        return response.status = 406
    }
}
