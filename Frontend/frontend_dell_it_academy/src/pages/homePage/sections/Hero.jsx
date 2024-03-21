import './css/Hero.css';
import { Carousel } from 'react-bootstrap';

// Componente que renderiza o carrossel da página inicial. Popularmente conhecido como Hero Section.

const Hero = () => { 

// Retorna um componente React com a estrutura do carrossel da página inicial.
  return (
    <div>
        <Carousel fade>
            <Carousel.Item>
                <a href='/add-betcard'>
                <img
                    className='item-1 d-block w-100'
                    src='src\assets\images\homepage_images\jovens_celular.png'
                    alt='jovens_no_celular'
                ></img>
                </a>                
                <Carousel.Caption>
                    <h3>Registre já suas apostas!</h3>
                    <p>Somente na Murakami Bets os melhores prêmios!</p>
                </Carousel.Caption>
            </Carousel.Item> 
            <Carousel.Item>
                <a href='/editions'>
                <img
                    className='item-2 d-block w-100'
                    src='src\assets\images\homepage_images\Autumn_Leafs.png'
                    alt='Autumn_Leafs'
                ></img>
                </a>
                <Carousel.Caption>
                    <h3>Veja todas as edições!</h3>
                    <p>Nossa edição de outono está por vir! Fique ligado!</p>
                </Carousel.Caption>
            </Carousel.Item>
            <Carousel.Item>
                <a href='/luckyclients'>
                <img
                    className='item-3 d-block w-100'
                    src='src\assets\images\homepage_images\Dinheiro.png'
                    alt='Reais'
                ></img>
                </a>
                <Carousel.Caption>
                    <h3>Confira os últimos vencedores!</h3>
                    <p>Suas chances de ganhar aumentadas!</p>
                </Carousel.Caption>
            </Carousel.Item>
        </Carousel>
    </div>
  )
}

export default Hero