package tp1.grailsgroovy

class BootStrap {

    def init = { servletContext ->
        def userInstance = new User(username: "username",
                password: "password",
                thumbnail: new Illustration(filename: "apple-touch-icon.png"))
                .save(flush:true, failOnError: true)

        (1..5).each{
            userInstance.addToAnnonces(
                    new Annonce(
                            title: "title" + it,
                            description: "description",
                            validTill: new Date(),
                            state: Boolean.TRUE
                    )
                    .addToIllustrations(new Illustration(filename: "apple-touch-icon.png"))
                    .addToIllustrations(new Illustration(filename: "apple-touch-icon.png"))
                    .addToIllustrations(new Illustration(filename: "apple-touch-icon.png"))
            )
        }
        userInstance.save(flush:true, failOnError: true)
    }
    def destroy = {
    }
}

