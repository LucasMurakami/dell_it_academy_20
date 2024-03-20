
import { Container, Row, Col, Button } from 'react-bootstrap';
import './css/CardComponent.css';
import CountNumberBetCard from '../dataComponent/CountNumberBetCard';
import { getLastEdition } from "../services/EditionService";
import { useEffect, useState } from 'react';

const CardComponent = () => {

    CardComponent.propTypes = {
    }
    useEffect(() => {
      getCurrentEdition();
  },[]);

  const [winnerData, setWinnerData] = useState([]);

  async function getCurrentEdition() {
    try {
      const response = await getLastEdition();
      console.log(response.data);
  
      setWinnerData(response.data);
      console.log(winnerData);
      console.log(winnerData.luckyClients)
      return response;
    } catch (error) {
      console.error(error);
    }
  } 

  return (
    <Container className='card'>
      <Row className='card-row'>
        <Col className='card-col'>
          <h2 className="card-title">INFORMAÇÕES DA EDIÇÃO ATUAL</h2>
        </Col>        
      </Row>
      <Row className='card-row'>
        <Col>
          <p className='card-text'>Rodadas do sorteio: {winnerData?.lotteryRounds ? winnerData.lotteryRounds : " --- "}</p>
        </Col>        
      </Row>
      <Row className='card-row'>
        <Col className='card-col'>
          <p className="card-text">Lista de números sorteados: {Array.isArray(winnerData?.luckyNumbers) && winnerData?.isActive === false ? winnerData.luckyNumbers.join(", ") : " --- "}</p>    
        </Col>          
      </Row>
      <Row className='card-row'>
        <Col className='card-col'>
          <p className='card-text'>Quantidade de apostas vencedoras: {winnerData?.luckyBets === 0 || winnerData?.luckyBets ? winnerData.luckyBets : " --- "}</p>
        </Col>        
      </Row>
      <Row className='card-row'>
        <Col className='card-col'>
          <p className='card-text'>Lista das apostas vencedoras: {Array.isArray(winnerData?.luckyClients) && winnerData.luckyClients.length > 0  ? winnerData.luckyClients.sort((a, b) => a.name.localeCompare(b.name)).map(client => client.name).join(", "): " --- "} </p>
        </Col>        
      </Row>
      <Row className='card-row'>
        <Col className='card-col'>
          <CountNumberBetCard />
        </Col>        
      </Row>
      <Row className='card-row'>
        <Col className='card-col'>
          <a className="card-link" href="/luckyclients"><button className="card-button-lastEdition">Clique aqui para ver todos ganhadores de todas edições!</button></a>
        </Col>      
      </Row> 
    </Container>       
    
  );
}

export default CardComponent