package tp1.grailsgroovy

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UserController {

    UserService userService
    IllustrationService illustrationService


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userService.list(params), model:[userCount: userService.count()]
    }

    def show(Long id) {
        respond userService.get(id)
    }

    def create() {
        respond new User(params)
    }

    def save(User user) {
        if (user == null) {
            notFound()
            return
        }

        def file= request.getFile("file")

        final String lexicon = "abcdefghijklmnopqrstuvwxyz1234567890"
        final Random rand = new Random()
        final Set<String> identifiers= new HashSet<String>()

        StringBuilder nameFile = new StringBuilder()
        while (nameFile.toString().length()==0){
            int length = rand.nextInt(5)+5
            for (int i=0; i<length; i++){
                nameFile.append(lexicon.charAt(rand.nextInt(lexicon.length())))
            }
            if(identifiers.contains(nameFile.toString())){
                nameFile=new StringBuilder()
            }
        }

        file.transferTo(new File(grailsApplication.config.myconf.assets_path+nameFile+".png"))
        user.thumbnail= new Illustration(filename: nameFile+".png")

        try {
            userService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond userService.get(id)
    }

    def update(User user) {
        println "test"
        if (user == null) {
            notFound()
            return
        }


        try {
            userService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*'{ respond user, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    /*def deleteIllustration(Long id){
        def idUser = params.idUser
        if (id==null || idUser ==null){
            notFound()
            return
        }
        def userInstance = userService.get(idUser)
        def illustrationInstance = illustrationService.get(id)

        userInstance.removeFromThumbnail
        userInstance.save(flush:true)
        illustrationInstance.delete(flush:true)

        redirect action: "edit", id: userInstance.id
    }*/

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
