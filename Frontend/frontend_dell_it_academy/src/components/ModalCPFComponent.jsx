import { Modal, Button } from 'react-bootstrap';
import propTypes from 'prop-types';
import './css/ModalComponent.css';

// Componente que renderiza um modal com as escolhas de registros que o Usuário já possui.
const ModalCPFComponent = ({ title, text, show, handleClose, handleButtonClick, btnTextConfirmar, clientHistory, handleGetIndex }) => {

  // Parâmetros do componente.
  ModalCPFComponent.propTypes = {
    title: propTypes.string.isRequired,
    text: propTypes.string.isRequired,
    show: propTypes.bool.isRequired,
    handleClose: propTypes.func.isRequired,
    handleButtonClick: propTypes.func.isRequired,
    btnTextConfirmar: propTypes.string.isRequired,
    clientHistory: propTypes.array.isRequired,
    handleGetIndex: propTypes.func.isRequired
  }

  // Retorna um componente React com a estrutura do modal.
  return (
    <Modal show={show} onHide={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>{title}</Modal.Title>
      </Modal.Header>
      <Modal.Body>        
        {text}
        <br/>
        {clientHistory.map((client, index) => {
            return (
                <Button className='btn-modalCPF' key={index} variant="primary" onClick={() => handleGetIndex(index)}>
                {client.name} - {index + 1}
                </Button>
            );
        })}
        </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Fechar
        </Button>
        <Button variant="primary" onClick={handleButtonClick}>
          {btnTextConfirmar}
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

export default ModalCPFComponent;
