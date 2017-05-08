package co.infinum.mvpexample

import co.infinum.mvpexample.data.interactors.pokedex.PokedexInteractor
import co.infinum.mvpexample.data.interactors.pokedex.PokedexListener
import co.infinum.mvpexample.data.models.Pokemon
import co.infinum.mvpexample.data.models.responses.PokemonListResponse
import co.infinum.mvpexample.ui.list.PokemonListMvp
import co.infinum.mvpexample.ui.list.PokemonListPresenter
import co.infinum.ultimate_drives.ui.BaseTest
import com.squareup.moshi.Types
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

class PokemonListPresenterTest : BaseTest() {

    @Mock
    lateinit var view: PokemonListMvp.View

    @Mock
    lateinit var interactor: PokedexInteractor

    var presenter: PokemonListPresenter? = null

    @Before
    fun setup() {
        presenter = PokemonListPresenter(view, interactor)
    }

    @Test
    fun dataLoadedAndShown() {
        val pokedexResponse: String = getFileAsString("pokedex.json")
        val pokedexType = Types.newParameterizedType(PokemonListResponse::class.java, Pokemon::class.java)
        val pokedexAdapter = moshi.adapter<PokemonListResponse>(pokedexType)
        val pokedex = pokedexAdapter.fromJson(pokedexResponse)

        Mockito.doAnswer { invocation ->
            (invocation.arguments[0] as PokedexListener).onPokemonsObtained(pokedex)
        }.`when`(interactor)?.getPokemons(any(PokedexListener::class.java))

        presenter?.init()
        verify(view).initUi()
        verify(view).showProgress()

        verify(view).setPokemonList(eq(pokedex.pokemons))
        verify(view).setEmptyViewVisibility(false)
        verify(view).hideProgress()
    }

    @Test
    fun emptyViewShownIfEmptyResponse() {
        val response = PokemonListResponse()
        Mockito.doAnswer { invocation ->
            (invocation.arguments[0] as PokedexListener).onPokemonsObtained(response)
        }.`when`(interactor)?.getPokemons(any(PokedexListener::class.java))

        presenter?.init()
        verify(view).initUi()
        verify(view).showProgress()

        verify(view).setEmptyViewVisibility(eq(true))
        verify(view).hideProgress()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun errorShown() {
        Mockito.doAnswer { invocation ->
            (invocation.arguments[0] as PokedexListener).onError("error")
        }.`when`(interactor)?.getPokemons(any(PokedexListener::class.java))

        presenter?.init()
        verify(view).initUi()
        verify(view).showProgress()

        verify(view).hideProgress()
        verify(view).showError(eq("error"))
        verifyNoMoreInteractions(view)
    }

    @Test
    fun cancel() {
        presenter?.cancel()
        verify(interactor).cancel()
        verifyNoMoreInteractions(view)
    }
}
