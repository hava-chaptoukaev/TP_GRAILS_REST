package tp1.grailsgroovy

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AnnonceController {

    AnnonceService annonceService
    IllustrationService illustrationService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond annonceService.list(params), model:[annonceCount: annonceService.count()]
    }

    def show(Long id) {
        respond annonceService.get(id)
    }

    def create() {
        respond new Annonce(params)
    }

    def save(Annonce annonce) {
        if (annonce == null) {
            notFound()
            return
        }

        def file= request.getFiles("file")

        file.each {
            final String lexicon = "abcdefghijklmnopqrstuvwxyz1234567890"
            final Random rand = new Random()
            final Set<String> identifiers = new HashSet<String>()

            StringBuilder nameFile = new StringBuilder()
            while (nameFile.toString().length() == 0) {
                int length = rand.nextInt(5) + 5
                for (int i = 0; i < length; i++) {
                    nameFile.append(lexicon.charAt(rand.nextInt(lexicon.length())))
                }
                if (identifiers.contains(nameFile.toString())) {
                    nameFile = new StringBuilder()
                }
            }
            it.transferTo(new File(grailsApplication.config.myconf.assets_path+nameFile+".png"))
            annonce.addToIllustrations(new Illustration(filename: nameFile+".png"))
        }

        try {
            annonceService.save(annonce)
        } catch (ValidationException e) {
            respond annonce.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'annonce.label', default: 'Annonce'), annonce.id]) as Object
                redirect annonce
            }
            '*' { respond annonce, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond annonceService.get(id)
    }

    def update(Annonce annonce) {
        if (annonce == null) {
            notFound()
            return
        }


        try {
            annonceService.save(annonce)
        } catch (ValidationException e) {
            respond annonce.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'annonce.label', default: 'Annonce'), annonce.id]) as Object
                redirect annonce
            }
            '*'{ respond annonce, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        annonceService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'annonce.label', default: 'Annonce'), id]) as Object
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    def deleteIllustration(Long id){
        def idAnnonce = params.idAnnonce
        if (id==null || idAnnonce ==null){
            notFound()
            return
        }
        def annonceInstance = annonceService.get(idAnnonce)
        def illustrationInstance = illustrationService.get(id)

        annonceInstance.removeFromIllustrations(illustrationInstance)
        annonceInstance.save(flush:true)
        illustrationInstance.delete(flush:true)

        redirect action: "edit", id: annonceInstance.id
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'annonce.label', default: 'Annonce'), params.id]) as Object
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
