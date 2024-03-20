import { Modal, Button } from 'react-bootstrap';
import propTypes from 'prop-types';

// Componente que renderiza um modal.
const ModalComponent = ({ title, text, show, handleClose, handleButtonClick, btnTextConfirmar }) => {

  // Parâmetros do componente.
  ModalComponent.propTypes = {
    title: propTypes.string.isRequired,
    text: propTypes.string.isRequired,
    show: propTypes.bool.isRequired,
    handleClose: propTypes.func.isRequired,
    handleButtonClick: propTypes.func.isRequired,
    btnTextConfirmar: propTypes.string.isRequired
  } 

  // Retorna um componente React com a estrutura do modal.
  return (
    <Modal show={show} onHide={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>{title}</Modal.Title>
      </Modal.Header>
      <Modal.Body>{text}</Modal.Body>
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

export default ModalComponent;
