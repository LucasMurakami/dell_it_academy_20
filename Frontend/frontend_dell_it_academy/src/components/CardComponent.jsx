import { Container, Row, Col} from 'react-bootstrap';
import propTypes from 'prop-types';
import './css/CardComponent.css';

// Componente que renderiza um card.
const CardComponent = ({onClickFunction, cardTitle, cardText1, cardText2, cardText3, btnText}) => {

  // Parâmetros do componente.
  CardComponent.propTypes = {
      onClickFunction: propTypes.func.isRequired,
      cardTitle: propTypes.string.isRequired,
      cardText1: propTypes.string.isRequired,
      cardText2: propTypes.string,
      cardText3: propTypes.string,
      btnText: propTypes.string.isRequired
  }

  // Retorna um componente React com a estrutura do card.
  return (
    <Container className='card'>
      <Row className='card-row'>
        <Col className='card-col'>          
          <h2 className="card-title">{cardTitle}</h2>
        </Col>
      </Row>           
      <Row>     
        <Col className='card-col' md={{ span: 10, offset: 1 }}>
          <p className="card-text">{cardText1}</p>
        </Col>
      </Row>
      <Row>
        <Col className='card-col' md={{ span: 10, offset: 1 }}>
          <p className='card-text'>{cardText2}</p>
        </Col>
      </Row>   
      <Row>
        <Col className='card-col' md={{ span: 10, offset: 1 }}>
          <p className='card-text'>{cardText3}</p>
        </Col>        
      </Row>
      <Row className='card-row'>
        <Col>          
          <button className="card-button card-button-text" onClick={onClickFunction}>{btnText}</button>
        </Col>
      </Row>  
    </Container>         
    
  );
}

export default CardComponent