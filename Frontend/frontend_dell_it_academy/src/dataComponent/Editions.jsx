import { useEffect, useState } from "react"
import { getAllEditions } from "../services/EditionService"
import { Container, Row, Col } from 'react-bootstrap';
import './css/DataComponents.css'

// Componente que renderiza a lista de edições.
const Editions = () => {

  // Variável global que armazena a lista de edições.
  const [Editions, setEditions] = useState([])

  // Função que atualiza a página em relação a lista de edições.
  useEffect(() => {
        listEditions();
  },[])

  // Função que busca a lista de edições.
  function listEditions() {
    getAllEditions().then(response => {
      setEditions(response.data);
    }).catch(error => {
       console.log(error)
    })
  }

  // Retorna um componente React com a estrutura da lista de edições. 
  return (
      <Container>
        <Row><h2 className="table-title text-center">Lista de edições</h2></Row>        
          <Col>
            <table className="table table-bordered table-striped">
              <thead>
                <tr>
                  <th className="text-center">Edição ID</th>
                  <th className="text-center">Número de apostas vencedoras</th>
                  <th className="text-center">Total de rodadas</th>
                  <th className="text-center">Números da sorte</th>
                </tr>
              </thead>
              <tbody>
                {
                  Editions.map(edition =>                                 
                    <tr key={edition.id}>
                      <td className="text-center">{edition.id}</td>
                      <td className="text-center">{edition.luckyBets}</td>
                      <td className="text-center">{edition.lotteryRounds}</td>
                      <td className="text-center">{ edition.isActive ? " Edição em andamento. "  : edition.luckyNumbers.map((luckyNumber) => luckyNumber).join(", ")}</td>                      
                    </tr>                                
                  )
                }
              </tbody>
            </table>
          </Col>
      </Container>
    )
}

export default Editions