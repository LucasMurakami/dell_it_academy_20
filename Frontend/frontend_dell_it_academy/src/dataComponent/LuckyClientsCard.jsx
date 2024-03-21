import { useState, useEffect } from "react";
import { getAllLuckyClients } from "../services/LuckyClientService";
import { Container, Row, Col } from 'react-bootstrap';
import { getLastEdition } from "../services/EditionService";
import './css/DataComponents.css'

// Componente que renderiza a lista de ganhadores por id.
const LuckyClients = () => {

    // Variável global que armazena a lista de ganhadores.
    const [LuckyClients, setLuckyClients] = useState([])

    useEffect(() => {
        listLuckyClients();
    },[])

    // Função que busca a lista de ganhadores por edição atual.
    async function listLuckyClients() {
      const currentEdition = await getCurrentEdition();
        getAllLuckyClients().then(response => {
            const filteredAndSortedClients = response.data.filter(client => client.betCardDTO.editionId === currentEdition.data.id).sort((a, b) => a.name.localeCompare(b.name));
            setLuckyClients(filteredAndSortedClients);
        }).catch(error => {
            console.log(error);
        });
    }


     // Função assíncrona que busca a edição atual.
     async function getCurrentEdition() {
      return getLastEdition().then(response => {  
          return response;
      }).catch(error => {
        console.error(error);
      })
    }
    
    // Retorna um componente React com a estrutura da lista de ganhadores.
    return (
        <Container className="container">
            <Row><h2 className="table-title text-center">Lista de Ganhadores</h2></Row>            
            <Col>
              <table className="table table-bordered table-striped">
                  <thead>
                      <tr>
                        <th className="text-center">Nome do(a) vencedor(a)</th>
                        <th className="text-center">Prêmio</th>
                        <th className="text-center">Edição</th>
                        <th className="text-center">Números apostados</th>
                      </tr>
                  </thead>
                  <tbody>
                    {
                      LuckyClients.map(luckyClient =>                                 
                        <tr key={luckyClient.id}>
                          <td className="text-center">{luckyClient.name}</td>
                          <td className="text-center">R$ {luckyClient.prize}</td>
                          <td className="text-center">Edição - {luckyClient.betCardDTO.editionId}</td>
                          <td className="text-center">{luckyClient.betCardDTO.numbers.map((item) => `${item.number}`).join(", ")}</td>
                        </tr>                                
                        )
                    }
                    </tbody>
              </table>
            </Col>
        </Container>
    )
}

export default LuckyClients