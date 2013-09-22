package relicious

import org.springframework.dao.DataIntegrityViolationException

class FavouriteController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [favouriteInstanceList: Favourite.list(params), favouriteInstanceTotal: Favourite.count()]
    }

    def create() {
        [favouriteInstance: new Favourite(params)]
    }

    def save() {
        def favouriteInstance = new Favourite(params)
        if (!favouriteInstance.save(flush: true)) {
            render(view: "create", model: [favouriteInstance: favouriteInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'favourite.label', default: 'Favourite'), favouriteInstance.id])
        redirect(action: "show", id: favouriteInstance.id)
    }

    def show(Long id) {
        def favouriteInstance = Favourite.get(id)
        if (!favouriteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'favourite.label', default: 'Favourite'), id])
            redirect(action: "list")
            return
        }

        [favouriteInstance: favouriteInstance]
    }

    def edit(Long id) {
        def favouriteInstance = Favourite.get(id)
        if (!favouriteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'favourite.label', default: 'Favourite'), id])
            redirect(action: "list")
            return
        }

        [favouriteInstance: favouriteInstance]
    }

    def update(Long id, Long version) {
        def favouriteInstance = Favourite.get(id)
        if (!favouriteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'favourite.label', default: 'Favourite'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (favouriteInstance.version > version) {
                favouriteInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'favourite.label', default: 'Favourite')] as Object[],
                          "Another user has updated this Favourite while you were editing")
                render(view: "edit", model: [favouriteInstance: favouriteInstance])
                return
            }
        }

        favouriteInstance.properties = params

        if (!favouriteInstance.save(flush: true)) {
            render(view: "edit", model: [favouriteInstance: favouriteInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'favourite.label', default: 'Favourite'), favouriteInstance.id])
        redirect(action: "show", id: favouriteInstance.id)
    }

    def delete(Long id) {
        def favouriteInstance = Favourite.get(id)
        if (!favouriteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'favourite.label', default: 'Favourite'), id])
            redirect(action: "list")
            return
        }

        try {
            favouriteInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'favourite.label', default: 'Favourite'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'favourite.label', default: 'Favourite'), id])
            redirect(action: "show", id: id)
        }
    }
}
