package relicious

import org.springframework.dao.DataIntegrityViolationException

class RecipeBookController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [recipeBookInstanceList: RecipeBook.list(params), recipeBookInstanceTotal: RecipeBook.count()]
    }

    def create() {
        [recipeBookInstance: new RecipeBook(params)]
    }

    def save() {
        def recipeBookInstance = new RecipeBook(params)
        if (!recipeBookInstance.save(flush: true)) {
            render(view: "create", model: [recipeBookInstance: recipeBookInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'recipeBook.label', default: 'RecipeBook'), recipeBookInstance.id])
        redirect(action: "show", id: recipeBookInstance.id)
    }

    def show(Long id) {
        def recipeBookInstance = RecipeBook.get(id)
        if (!recipeBookInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'recipeBook.label', default: 'RecipeBook'), id])
            redirect(action: "list")
            return
        }

        [recipeBookInstance: recipeBookInstance]
    }

    def edit(Long id) {
        def recipeBookInstance = RecipeBook.get(id)
        if (!recipeBookInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'recipeBook.label', default: 'RecipeBook'), id])
            redirect(action: "list")
            return
        }

        [recipeBookInstance: recipeBookInstance]
    }

    def update(Long id, Long version) {
        def recipeBookInstance = RecipeBook.get(id)
        if (!recipeBookInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'recipeBook.label', default: 'RecipeBook'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (recipeBookInstance.version > version) {
                recipeBookInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'recipeBook.label', default: 'RecipeBook')] as Object[],
                          "Another user has updated this RecipeBook while you were editing")
                render(view: "edit", model: [recipeBookInstance: recipeBookInstance])
                return
            }
        }

        recipeBookInstance.properties = params

        if (!recipeBookInstance.save(flush: true)) {
            render(view: "edit", model: [recipeBookInstance: recipeBookInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'recipeBook.label', default: 'RecipeBook'), recipeBookInstance.id])
        redirect(action: "show", id: recipeBookInstance.id)
    }

    def delete(Long id) {
        def recipeBookInstance = RecipeBook.get(id)
        if (!recipeBookInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'recipeBook.label', default: 'RecipeBook'), id])
            redirect(action: "list")
            return
        }

        try {
            recipeBookInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'recipeBook.label', default: 'RecipeBook'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'recipeBook.label', default: 'RecipeBook'), id])
            redirect(action: "show", id: id)
        }
    }
}
