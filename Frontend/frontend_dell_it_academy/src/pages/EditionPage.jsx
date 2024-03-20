import { createEdition, getLastEdition, searchWinners } from "../services/EditionService";
import { useEffect, useState } from "react";
import { Container, Row, Col } from 'react-bootstrap';

import ModalComponent from "../components/ModalComponent";
import CardComponent from "../components/CardComponent";
import CardLastEditionInfoComponent from "../components/CardLastEditionInfoComponent";

// Arquivo que renderiza a página de edições. Nela é possível criar uma nova edição e apurar os resultados da edição atual, além de ver informações sobre a última edição.

const Edition = () => {  

  // Variável para controlar a exibição do modal de apuração.
  const [showModalApuration, setShowModalApuration] = useState(false);

  // Variável para controlar a exibição do modal de sucesso ao criar uma nova edição.
  const [showModalEditionSuccess, setShowModalEditionSuccess] = useState(false);

  // Variável para controlar a exibição do modal de falha ao criar uma nova edição.
  const [showModalEditionFail, setShowModalEditionFail] = useState(false);

  // Variável para atualizar a página quando novas informações da edição ou da apuração são encontradas.
  const [refresh, setRefresh] = useState(0)

  // Funções para controlar a exibição do modal de apuração.
  const handleCloseApuration = () => setShowModalApuration(false);
  const handleShowApuration = () => setShowModalApuration(true);

  // Funções para controlar a exibição do modal de sucesso ao criar uma nova edição.
  const handleCloseEditionSuccess = () => setShowModalEditionSuccess(false);
  const handleShowEditionSuccess = () => setShowModalEditionSuccess(true);

  // Funções para controlar a exibição do modal de falha ao criar uma nova edição.
  const handleCloseEditionFail = () => setShowModalEditionFail(false);
  const handleShowEditionFail = () => setShowModalEditionFail(true);

  // Função que busca constantemente a última edição, por meio da dependência refresh. Logo, a página sempre estará atualizada com as informações mais recentes da última edição. 
  useEffect(() => {
    getCurrentEdition();
},[refresh]);

  // Função que cria uma nova edição no backend.
   async function createEditionBackend(e) {
    e.preventDefault(); 

    const lastEdition = await getCurrentEdition();

    if(lastEdition !== undefined) {
      if(lastEdition.data.isActive) {
        handleShowEditionFail();
        return;
      }
    }

    const edition = {};

    createEdition(edition).then((response) => {
      console.log(response.data);          
      handleShowEditionSuccess();
    }).catch((error) => {
      console.log(error);
    });

    setRefresh(refresh + 1);
  }

  // Função que faz a apuração dos resultados da edição atual.
  async function runResultsOfEdition(e) {
    e.preventDefault();

    handleCloseApuration();
    const lastEdition = await getCurrentEdition();   

    console.log(lastEdition.data);
    if(lastEdition.data.id === null) {      
      alert('Nenhuma edição foi encontrada. Inicie uma edição para poder ir para fase de apuração!');
      return;   
    }

    if (lastEdition.data.betCards.length === 0) {
      alert('Não existem apostas para serem apuradas.');
      return;
    }

    searchWinners().then((response) => { 
      console.log(response.data); 
    }).catch((error) => {
      console.log(error);
    });

    setRefresh(refresh + 1);
  }

  // Função que busca a última edição.
  async function getCurrentEdition() {
    try {
      const response = await getLastEdition();
      return response;
    } catch (error) {
      console.error(error);
    }
  }   

  // Função que redireciona o usuário para a página de apostas.
  function betPage() {
    window.location.href = '/add-betcard';
  }

  // Retorna um componente React com a estrutura da página de edições.
  return (
    <Container>      
      <Row>
        <Col>
          <CardComponent
            onClickFunction={createEditionBackend}
            cardTitle="EDIÇÃO"
            cardText1="Comece uma nova edição e esteja livre para apostar quantas vezes quiser!"
            btnText="Clique aqui para iniciar uma nova edição"
          />
          {showModalEditionSuccess && <ModalComponent 
            title="Sucesso!"
            text="Agora você pode apostar nesta edição!" 
            show={showModalEditionSuccess} 
            handleClose={handleCloseEditionSuccess} 
            handleButtonClick={betPage} 
            btnTextConfirmar="Apostar!"
          />}
          {showModalEditionFail && <ModalComponent 
            title="Erro!" 
            text="Não foi possível iniciar uma nova edição! Parece que uma edição já está em atividade! Vá apostar!" 
            show={showModalEditionFail} 
            handleClose={handleCloseEditionFail} 
            handleButtonClick={handleCloseEditionFail} 
            btnTextConfirmar="Confimar"
          />}
        </Col>
        <Col>
          <CardComponent           
            onClickFunction={handleShowApuration}
            cardTitle="APURAÇÃO"          
            cardText1="Apure os resultados para saber os resultados da edição atual!"
            btnText="Clique aqui para iniciar a apuração!"
          /> 
          {showModalApuration && <ModalComponent
            title="Apuração" 
            text="Deseja realmente apurar os resultados?" 
            show={showModalApuration} 
            handleClose={handleCloseApuration} 
            handleButtonClick={runResultsOfEdition} 
            btnTextConfirmar="Confirmar"
          />}
        </Col>  
      </Row>      
      <Row>
        <Col>
          <CardLastEditionInfoComponent key={refresh}/>
        </Col>
      </Row>
    </Container>
  )
};  

export default Edition
