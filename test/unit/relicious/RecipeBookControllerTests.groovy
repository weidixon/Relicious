package relicious



import org.junit.*
import grails.test.mixin.*

@TestFor(RecipeBookController)
@Mock(RecipeBook)
class RecipeBookControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/recipeBook/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.recipeBookInstanceList.size() == 0
        assert model.recipeBookInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.recipeBookInstance != null
    }

    void testSave() {
        controller.save()

        assert model.recipeBookInstance != null
        assert view == '/recipeBook/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/recipeBook/show/1'
        assert controller.flash.message != null
        assert RecipeBook.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/recipeBook/list'

        populateValidParams(params)
        def recipeBook = new RecipeBook(params)

        assert recipeBook.save() != null

        params.id = recipeBook.id

        def model = controller.show()

        assert model.recipeBookInstance == recipeBook
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/recipeBook/list'

        populateValidParams(params)
        def recipeBook = new RecipeBook(params)

        assert recipeBook.save() != null

        params.id = recipeBook.id

        def model = controller.edit()

        assert model.recipeBookInstance == recipeBook
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/recipeBook/list'

        response.reset()

        populateValidParams(params)
        def recipeBook = new RecipeBook(params)

        assert recipeBook.save() != null

        // test invalid parameters in update
        params.id = recipeBook.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/recipeBook/edit"
        assert model.recipeBookInstance != null

        recipeBook.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/recipeBook/show/$recipeBook.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        recipeBook.clearErrors()

        populateValidParams(params)
        params.id = recipeBook.id
        params.version = -1
        controller.update()

        assert view == "/recipeBook/edit"
        assert model.recipeBookInstance != null
        assert model.recipeBookInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/recipeBook/list'

        response.reset()

        populateValidParams(params)
        def recipeBook = new RecipeBook(params)

        assert recipeBook.save() != null
        assert RecipeBook.count() == 1

        params.id = recipeBook.id

        controller.delete()

        assert RecipeBook.count() == 0
        assert RecipeBook.get(recipeBook.id) == null
        assert response.redirectedUrl == '/recipeBook/list'
    }
}
