import './css/ClientForm.css';
import { Container, Form } from 'react-bootstrap';
import propTypes from 'prop-types';

// Componente que renderiza o formulário de cadastro de clientes.
const ClientForm = ( {handleNameChange, handleCpfChange, name, displayCpf} ) => {

  // Parâmetros do componente.
  ClientForm.propTypes = {
    handleNameChange: propTypes.func.isRequired,
    handleCpfChange: propTypes.func.isRequired,
    name: propTypes.string.isRequired,
    displayCpf: propTypes.string.isRequired   
  };

  // Retorna um componente React com a estrutura do formulário de cadastro de clientes.
  return (

    <Container className="form-client">
      <Form className="form-container">
        <Form.Group>
          <Form.Label className='form-label-client'>Nome:</Form.Label>
          <Form.Control size="lg" type="text" placeholder="Nome" value={name} onChange={handleNameChange} required />
        </Form.Group>
        <Form.Group>
          <Form.Label className='form-label-cpf'>CPF:</Form.Label>
          <Form.Control size="lg" type="text" placeholder="CPF" value={displayCpf} onChange={handleCpfChange} required />
        </Form.Group>
      </Form>
    </Container>

  );
}

export default ClientForm;
