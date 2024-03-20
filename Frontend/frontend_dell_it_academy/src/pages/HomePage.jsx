import Hero from "./homePage/sections/Hero"
import Cards from "./homePage/sections/Cards"
import CardLastEditionInfoComponent from "../components/CardLastEditionInfoComponent"

// Arquivo que renderiza a página inicial do site.

const Home = () => {

  // Retorna um componente React com a estrutura da página inicial.
  return (
    
    <div>
        <section>
            <Hero />
        </section>
        <section>
            <Cards />
        </section>            
        <section>
          <CardLastEditionInfoComponent />
        </section>
    </div>
    
  )
}

export default Home