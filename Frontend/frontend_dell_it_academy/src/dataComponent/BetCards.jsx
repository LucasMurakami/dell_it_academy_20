import { useEffect, useState } from "react"
import { getAllBetCards } from "../services/BetCardService"
import { getClientById } from "../services/ClientService"
import { Container, Row, Col } from 'react-bootstrap';
import './css/DataComponents.css'

// Componente que renderiza a lista de apostas.
const BetCards = () => {

  // Variável global que armazena a lista de apostas.
  const [BetCards, setBetCards] = useState([])

  // Função assíncrona que busca o nome do cliente.
  async function getNameClient(clientId) {
      try {
          const response = await getClientById(clientId);
          return response.data;
      } catch (error) {
          console.log(error);
      }
  }
    
  // Função assíncrona que busca a lista de apostas e junta com o nome do cliente, uma vez que a BetCardDTO somente possui um Long id do cliente.
  async function listCardBets(getNameClient) {
      try {
          const response = await getAllBetCards();
          const betCards = await Promise.all(response.data.map(async betCard => {
              const client = await getNameClient(betCard.clientId);
              return { ...betCard, clientName: client.name };
          }));
          setBetCards(betCards);
      } catch (error) {
        console.log(error);
      }
  }
    
  // Função que atualiza a página uma vez a cada entrada, para que a lista de apostas seja atualizada.
  useEffect(() => {
      listCardBets(getNameClient);
  }, []);
    
    // Retorna um componente React com a estrutura da lista de apostas.
    return (
      <Container>
        <Row><h2 className="table-title text-center">Lista de apostas</h2></Row>
        <Col>
          <table className="table table-bordered table-striped">
            <thead>
              <tr>
                <th className="text-center">Aposta ID</th>
                <th className="text-center">Nome do Apostador(a)</th>
                <th className="text-center">Edição da aposta</th>
                <th className="text-center">Números Apostados</th>
              </tr>
            </thead>
            <tbody>
              {
                BetCards.map(betCard =>                                 
                  <tr key={betCard.id}>
                    <td className="text-center">{betCard.id}</td>
                    <td className="text-center">{betCard.clientName}</td>
                    <td className="text-center">Edição - {betCard.editionId}</td>
                    <td className="text-center">[{betCard.numbers.map((obj) => obj.number).join(", ")}]</td>
                </tr>                                
                )                            
              }
            </tbody>
          </table>
        </Col>
      </Container>
    )
    
}

export default BetCards