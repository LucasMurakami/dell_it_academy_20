import './css/NumberBetCardForm.css';
import { Container, Row, Col, Form } from 'react-bootstrap';

import PropTypes from 'prop-types';

// Componente que renderiza o formulário de apostas numéricas.

const NumberBetCard = ({handleNumberChange, selectedNumbers, handleSurprise, clearSelectedNumbers}) => {

  // Parâmetros do componente.
  NumberBetCard.propTypes = {
    handleNumberChange: PropTypes.func.isRequired,
    selectedNumbers: PropTypes.array.isRequired,
    handleSurprise: PropTypes.func.isRequired,
    clearSelectedNumbers: PropTypes.func.isRequired,
  };
  
  // Retorna um componente React com a estrutura do formulário de apostas numéricas.
    return (
      <Container>
        <Row className="justify-content-center">
          <Col className='col-custom'>
            <h2 className="number-title text-center">Escolha 5 números</h2>
            <Form className="number-selector">
              <Form.Group>
                {[...Array(5).keys()].map(i => (
                  <Row key={i} className="justify-content-center number-row">
                    {[...Array(10).keys()].map(j => {
                      const number = i * 10 + j + 1;
                      const isSelected = selectedNumbers.includes(number);
                      return (
                        <Col xs={5} sm={5} md={5} lg={5} xl={1} key={number}>
                          <button className={`number-button ${isSelected ? 'selected' : ''}`} onClick={(e) => handleNumberChange(e, number)}>
                            {number}
                          </button>
                        </Col>
                      );
                    })}
                  </Row>
                ))}
                <Row className="justify-content-evenly">
                  <Col xs="auto">            
                    <button type="button" className="clear-button" onClick={clearSelectedNumbers}>Limpar</button>
                  </Col> 
                  <Col xs="auto">            
                    <button type="button" className="surprise-button" onClick={handleSurprise}>Surpresa</button> 
                  </Col>
                </Row>
               </Form.Group>
              </Form>              
          </Col>        
        </Row>          
      </Container>
            
  );
}


export default NumberBetCard;
