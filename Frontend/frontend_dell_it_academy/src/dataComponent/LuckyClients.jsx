import { useState, useEffect } from "react";
import { getAllLuckyClients } from "../services/LuckyClientService";
import { Container, Row, Col } from 'react-bootstrap';
import './css/DataComponents.css'

// Componente que renderiza a lista de ganhadores.
const LuckyClients = () => {

    // Variável global que armazena a lista de ganhadores.
    const [LuckyClients, setLuckyClients] = useState([])

    useEffect(() => {
        listLuckyClients();
    },[])

    // Função que busca a lista de ganhadores.
    function listLuckyClients() {
        getAllLuckyClients().then(response => {
            setLuckyClients(response.data);
            console.log(response.data)
        }).catch(error => {
             console.log(error)
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
                      </tr>
                  </thead>
                  <tbody>
                    {
                      LuckyClients.map(luckyClient =>                                 
                        <tr key={luckyClient.id}>
                          <td className="text-center">{luckyClient.name}</td>
                          <td className="text-center">R$ {luckyClient.prize}</td>
                          <td className="text-center">Edição - {luckyClient.editionId}</td>
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