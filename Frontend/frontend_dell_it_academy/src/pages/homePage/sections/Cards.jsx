import { Container, Row, Col } from 'react-bootstrap';
import CardComponent from '../../../components/CardComponent';

// Componente que renderiza os cards da página inicial.

const Cards = () => {

    // Função que redireciona o usuário para a página de apostas.
    function goToBetCard() {
        window.location.href = '/add-betcard';
    }

    // Função que redireciona o usuário para a página de edições.
    function goToEditions() {
        window.location.href = '/editions';
    }

    // Retorna um componente React com a estrutura dos cards da página inicial.
  return (
    <Container className='cards'>
      <Row className='cards-row'>
        <Col>
          <CardComponent 
            onClickFunction={goToBetCard}
            cardTitle={'Aposte agora!'}
            cardText1={'- Escolha seus números favoritos para apostar!'}
            cardText2={'- Aposte quantas vezes quiser!'}
            cardText3={'- Resultados instantâneos!'}
            btnText={'Apostar agora'}
          />
        </Col>
        <Col>
          <CardComponent 
            onClickFunction={goToEditions}
            cardTitle={'Acompanhe a lista de edições!'}
            cardText1={'- Veja todas as edições já realizadas'}
            cardText2={'- Confira os números sorteados'}
            cardText3={'- Prêmios incríveis!'}
            btnText={'Ver edições'}
          />
        </Col>
      </Row>     
    </Container>
  )
}

export default Cards