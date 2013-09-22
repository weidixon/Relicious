package relicious



import org.junit.*
import grails.test.mixin.*

@TestFor(FavouriteController)
@Mock(Favourite)
class FavouriteControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/favourite/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.favouriteInstanceList.size() == 0
        assert model.favouriteInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.favouriteInstance != null
    }

    void testSave() {
        controller.save()

        assert model.favouriteInstance != null
        assert view == '/favourite/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/favourite/show/1'
        assert controller.flash.message != null
        assert Favourite.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/favourite/list'

        populateValidParams(params)
        def favourite = new Favourite(params)

        assert favourite.save() != null

        params.id = favourite.id

        def model = controller.show()

        assert model.favouriteInstance == favourite
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/favourite/list'

        populateValidParams(params)
        def favourite = new Favourite(params)

        assert favourite.save() != null

        params.id = favourite.id

        def model = controller.edit()

        assert model.favouriteInstance == favourite
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/favourite/list'

        response.reset()

        populateValidParams(params)
        def favourite = new Favourite(params)

        assert favourite.save() != null

        // test invalid parameters in update
        params.id = favourite.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/favourite/edit"
        assert model.favouriteInstance != null

        favourite.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/favourite/show/$favourite.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        favourite.clearErrors()

        populateValidParams(params)
        params.id = favourite.id
        params.version = -1
        controller.update()

        assert view == "/favourite/edit"
        assert model.favouriteInstance != null
        assert model.favouriteInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/favourite/list'

        response.reset()

        populateValidParams(params)
        def favourite = new Favourite(params)

        assert favourite.save() != null
        assert Favourite.count() == 1

        params.id = favourite.id

        controller.delete()

        assert Favourite.count() == 0
        assert Favourite.get(favourite.id) == null
        assert response.redirectedUrl == '/favourite/list'
    }
}
