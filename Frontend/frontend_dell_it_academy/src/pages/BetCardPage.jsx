
import 'bootstrap/dist/css/bootstrap.min.css';
import { useEffect, useState } from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap';

import './css/BetCardPage.css'

import { createClient, getClientByCPF, putBetCardsClient } from '../services/ClientService';
import { getLastEdition } from '../services/EditionService';
import { createBetCard } from '../services/BetCardService';
import { createNumberBetCard } from '../services/NumberBetCard.service';
import ModalComponent from '../components/ModalComponent';
import ModalCPFComponent from '../components/ModalCPFComponent';
import Client from '../forms/ClientForm';
import NumberBetCards from '../forms/NumberBetCardForm';

const BetCard = () => {

  // Variáveis para os dados do Cliente
  const [name, setName] = useState('');
  const [cpf, setCpf] = useState('');

  // Variável para mostrar o CPF formatado na tela.
  const [displayCpf, setDisplayCpf] = useState('');

  // Variáveis globais para recebimento de parâmetros de componentes filhos.
  const [isClientValid, setIsClientValid] = useState(false);
  const [isOldClient, setIsOldClient] = useState(false); 
  const [clientHistory, setClientHistory] = useState([]);
  const [indexHistory, setIndexHistory] = useState(0);

  // Números escolhidos pelo cliente
  const [selectedNumbers, setSelectedNumbers] = useState([]);
  const [isSelectedNumberValid, setIsSelectedNumberValid] = useState(false);

  // Variáveis para controle de modais.
  const [isEditionShow, setIsEditionShow] = useState(false);
  const [isClientValidShow, setIsClientValidShow] = useState(false);
  const [isBetCardCreated, setIsBetCardCreated] = useState(false);

  // Funções para controlar a exibição do modal de edição.
  const isEditionShowHandler = () => setIsEditionShow(true);
  const isEditionCloseHandler = () => setIsEditionShow(false);

  // Funções para controlar a exibição do modal de validação do cliente.
  const isClientValidShowHandler = () => setIsClientValidShow(true);
  const isClientValidCloseHandler = () => setIsClientValidShow(false);

  // Funções para controlar a exibição do modal de cliente com histórico.  
  const isOldClientShowHandler = () => setIsOldClient(true);
  const isOldClientCloseHandler = () => setIsOldClient(false);

  // Funções para controlar a exibição do modal de aposta criada.  
  const isBetCardCreatedShowHandler = () => setIsBetCardCreated(true);
  const isBetCardCreatedCloseHandler = () => setIsBetCardCreated(false);

  // Funções para controlar a exibição do modal de validação de números escolhidos.
  const isSelectedNumbersValidShowHandler = () => setIsSelectedNumberValid(true);
  const isSelectedNumbersValidCloseHandler = () => setIsSelectedNumberValid(false);
  
  // Função que controla a criação de um novo registro de cliente, caso já tenha um histórico, porém, queira criar um novo registro.
  const handleUserNewRegistry = () => {
    setIsOldClient(false);
    continueCreateBetCardBackend();
  }

  // Função que controla a atualização de um registro de cliente, caso já tenha um histórico e queira utilizar seu registro antigo.
  const handleIndexSelection = (index) => {
    setIndexHistory(index);
    setIsOldClient(false);
    continueCreateBetCardBackend(index);
  };


  // Verificação constante do nome e cpf para validação. 
  useEffect(() => {
    validateClient();
  }, [name, cpf]);
   
  // Função que redireciona o usuário para a página de edição. Usado no Modal de erro ao criar aposta.
  function goToEditionPage() {
    window.location.href = '/edition';
  } 
  
  // Função que recebe o nome do usuário.

  function handleNameChange(event) {
    setName(event.target.value);
  }

  // Função que formata o CPF do usuário para tela.

  function formatCpf(value) {
    const onlyNumbers = value.split('').filter(char => char >= '0' && char <= '9');
    let formattedCpf = '';
    for (let i = 0; i < onlyNumbers.length; i++) {
      if (i === 3 || i === 6) {
        formattedCpf += '.';
      }
      if (i === 9) {
        formattedCpf += '-';
      }
      formattedCpf += onlyNumbers[i];
    }
    return formattedCpf;
  }

  // Função que recebe a data do cpf.

  function handleCpfChange(event) {
    const onlyNumbers = event.target.value.split('').filter(char => char >= '0' && char <= '9');
    if (onlyNumbers.length <= 11) {
      setCpf(onlyNumbers.join(''));
      setDisplayCpf(formatCpf(event.target.value));
    }
  }

  // Função que lida com a mudança de números.

  function handleNumberChange (e, number) {
    e.preventDefault();
    if (selectedNumbers.includes(number)) {
      setSelectedNumbers(selectedNumbers.filter(n => n !== number));
    } else if (selectedNumbers.length < 5) {
      setSelectedNumbers([...selectedNumbers, number]);
    }
  }

  // Função que gera uma surpresinha ao usuário.

  const handleSurprise = () => {
    const numbers = [];
    while (numbers.length < 5) {
      const number = Math.floor(Math.random() * 50) + 1;
      if (!numbers.includes(number)) {
        numbers.push(number);
      }
    }
    setSelectedNumbers(numbers);
  };

  // Função que limpa os números selecionados.

  function clearSelectedNumbers() {
    setSelectedNumbers([]);
  }

  //Início da função que cria a aposta do cliente.
  
  async function createBetCardBackend() {
    
    const currentEdition = await getLastEdition();

    console.log(currentEdition.data);
  
    if(!currentEdition.data.isActive) {
      isEditionShowHandler();
      return;
    }

    if(isClientValid === false) {
      isClientValidShowHandler();
      return;
    }

    if(selectedNumbers.length < 5) {
      isSelectedNumbersValidShowHandler();
      return;
    }
    
    const clientHistory = await getClientByCPF(cpf);
  
    if(clientHistory.data.length > 0) {
      setClientHistory(clientHistory.data);      
      setIndexHistory(0);
      isOldClientShowHandler();
    } else {
      continueCreateBetCardBackend(currentEdition);
    }
  }
  
  // Continuação da função que cria a aposta do cliente a partir da seleção do usuário. 
  async function continueCreateBetCardBackend(index) {

    const currentEdition = await getLastEdition();

    let currentClient = null;

    if(index >= 0) {      
      currentClient = clientHistory[index];
      updateBetCardsClient(currentClient, currentEdition);
    } else {
      currentClient = await createClientData();
      createBetCardClient(currentClient, currentEdition)
    }  
  }

  // Função que cria a aposta do cliente para um novo registro.
  async function createBetCardClient(currentClient, currentEdition) {

    console.log(currentClient);
    console.log(currentEdition);

    if(currentEdition.data.id === null) {
      isEditionShowHandler();      
      return;
    }
  
    if(selectedNumbers.length < 5) {
      isSelectedNumbersValidShowHandler();
      return;
    }

    console.log(currentClient.data);
    console.log(currentEdition.data);

    const clientId = currentClient.data.id;
    const currentEditionId = currentEdition.data.id;
    const BetCard = {clientId: clientId, editionId: currentEditionId, numbers: []};      
    const response = await createBetCard(BetCard);
    createNumberBetCardBackend(response.data.id);
    console.log(response.data);
  
    isBetCardCreatedShowHandler();
    return response;
  }

  // Função que atualiza a aposta do cliente para um registro já existente.
  async function updateBetCardsClient(currentClient, currentEdition) {
    console.log(currentClient);    
    console.log(currentClient.id);
    const response = await putBetCardsClient(currentClient.id, {betCardDTO: { clientId: currentClient.id,editionId: currentEdition.data.id, numbers: []}});
    console.log(response.data);
    console.log(response);
    createNumberBetCardBackend(response.data.betCardDTO.id);
    console.log(response.data);

    isBetCardCreatedShowHandler();
    return response;
  }
  
  // Função que cria um novo registro de cliente.
  async function createClientData() {
    const client = {name: name, cpf: cpf};
    const response = await createClient(client);
    return response;
  }
  
  async function createNumberBetCardBackend(betCardId) {
    const numbersBetCard = createArrayOfNumberBetCard(selectedNumbers, betCardId);
    const promises = numbersBetCard.map(numberBetCard => createNumberBetCard(numberBetCard));
    
    const responses = await Promise.all(promises);
    return responses;
  }
  
  // Função que cria um array de números apostados.
  function createArrayOfNumberBetCard(chosenNumbers, betCardId) {
    let createPromises = [];

    chosenNumbers.forEach(number => {
      const numberBetCard = {number: number, betCardId: betCardId};
      createPromises.push(numberBetCard);
    });

    return createPromises;
  }  

  // Função que valida o cliente.
  function validateClient() {
    if (name.length > 0 && cpf.length === 11) {
      setIsClientValid(true);
    } else {
      setIsClientValid(false);
    }
  }

  // Retorna um componente React com a estrutura da página de apostas.
  return (
    <Container className='betCard-page'>      
      <Row>
        <Col>          
          <NumberBetCards
            handleNumberChange={handleNumberChange}
            selectedNumbers={selectedNumbers}
            handleSurprise={handleSurprise}
            clearSelectedNumbers={clearSelectedNumbers}
          />
        </Col>
        <Col>   
          <Client 
            handleNameChange={handleNameChange}
            handleCpfChange={handleCpfChange}
            name={name}
            displayCpf={displayCpf}
          />    
        </Col>
      </Row>
      <Row className='betCard-page-row-btn'>
        <Col>          
          <Button className='betCard-page-btn' onClick={createBetCardBackend}>Apostar</Button>
          {isEditionShow && (
            <ModalComponent 
              title='Erro ao criar aposta!' 
              text='Nenhuma edição foi encontrada. Inicie uma edição para poder apostar! Vá para a página de cadastro de edição!' 
              show={isEditionShow} 
              handleClose={isEditionCloseHandler}
              handleButtonClick={goToEditionPage}
              btnTextConfirmar='Ir para página de edição'
            />
          )}
          {isClientValidShow && (
            <ModalComponent 
              title='Erro ao criar aposta!' 
              text='Preencha corretamente os campos de nome e CPF para poder apostar!' 
              show={isClientValidShow} 
              handleClose={isClientValidCloseHandler}
              handleButtonClick={isClientValidCloseHandler}
              btnTextConfirmar='Confirmar'
            />
          )}
          {isOldClient && (
            <ModalCPFComponent 
              title='CPF já cadastrado!' 
              text='Você pode usar seu cadastro anterior ou criar um novo! Selecione um anterior ou clique em confirmar para criar um novo cadastro!' 
              show={isOldClient} 
              handleClose={isOldClientCloseHandler}
              handleButtonClick={handleUserNewRegistry}
              btnTextConfirmar='Confirmar'
              clientHistory={clientHistory}
              handleGetIndex={handleIndexSelection}
            />
          )}
          {isBetCardCreated && (
            <ModalComponent 
              title='Aposta realizada com sucesso!' 
              text='Sua aposta foi realizada com sucesso!' 
              show={isBetCardCreated} 
              handleClose={isBetCardCreatedCloseHandler}
              handleButtonClick={isBetCardCreatedCloseHandler}
              btnTextConfirmar='Confirmar'
            />
          )}
          {isSelectedNumberValid && (
            <ModalComponent 
              title='Erro ao criar aposta!' 
              text='Selecione 5 números para poder apostar!' 
              show={isSelectedNumberValid} 
              handleClose={isSelectedNumbersValidCloseHandler}
              handleButtonClick={isSelectedNumbersValidCloseHandler}
              btnTextConfirmar='Confirmar'
            />
          )}
        </Col>
      </Row>  
    </Container>
  );  
}

export default BetCard;

