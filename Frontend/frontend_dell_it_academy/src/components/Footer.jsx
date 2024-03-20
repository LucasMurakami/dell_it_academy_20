import 'bootstrap/dist/css/bootstrap.min.css';

// Componente que renderiza o rodapé da página.
const Footer = () => {
  // Retorna um componente React com a estrutura do rodapé.
  return (
    <footer className="footer mt-auto py-3 bg-dark fixed-bottom">
      <div className="container text-center" >
        <span className="d-none d-lg-inline" style={{color: 'white'}} >© 2024 Lucas Kaito Murakami. All rights reserved.</span>
      </div>
    </footer>
  );
};

export default Footer;
