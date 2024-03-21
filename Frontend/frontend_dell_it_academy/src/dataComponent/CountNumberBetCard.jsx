import { useEffect, useState } from "react"
import { findHighestNumbersByEditionId } from "../services/NumberBetCard.service"
import { Container, Row, Col } from 'react-bootstrap';
import './css/DataComponents.css'
import { getLastEdition } from "../services/EditionService";

// Componente que renderiza a lista de apostas.
const CountNumberBetCard = () => {

  // Variável global que armazena a lista de números apostados.
  const [mostNumbersBetted, setMostNumbersBetted] = useState([])

  // Variável global que armazena a edição atual.
  const [currentEditionState, setCurrentEditionState] = useState([])

  // Função que atualiza a página em relação aos números mais apostados.
  useEffect(() => {
        listMostNumbersBettedByEdition();
  },[])

  // Função assíncrona que busca a lista de números mais apostados.
  async function listMostNumbersBettedByEdition() {
    const currentEdition = await getCurrentEdition();
    if (currentEdition && currentEdition.data) {
      setCurrentEditionState(currentEdition.data);
      if(currentEdition.data.id !== null) {
        findHighestNumbersByEditionId(currentEdition.data.id).then(response => {
          setMostNumbersBetted(response.data);
        }).catch(error => {
          console.log(error)
        })
      }
    }
  }  

    // Função assíncrona que busca a edição atual.
  async function getCurrentEdition() {
    return getLastEdition().then(response => {  
        return response;
    }).catch(error => {
      console.error(error);
    })
  }

    // Retorna um componente React com a estrutura da lista de números mais apostados
    return (
       <Container>
        <Row><h2 className="table-title text-center">Lista de números mais apostados</h2></Row>
          <Col>
            <table className="table table-bordered table-striped">
              <thead>
                <tr>
                  <th>Número de aposta</th>
                  <th>Quantidade de vezes apostado</th>
                </tr>
              </thead>
              <tbody>
                {(!currentEditionState || !currentEditionState.isActive) ?
                  mostNumbersBetted.slice().sort((a, b) => b.count - a.count).map(mostNumberBetted =>                                 
                    <tr key={mostNumberBetted.id}>
                      <td>{mostNumberBetted.number}</td>
                      <td>{mostNumberBetted.count}</td>
                    </tr>                                
                  ) : <></>
                }
              </tbody>
            </table>
          </Col>
        </Container>

    )
}

export default CountNumberBetCard