// Arquivo que salvará as constantes utilizadas na navbar.
export const navLinks = [
    { 
        href: "/",
        label: "Página Inicial"
    },
    { 
        href: "#", 
        label: "Edições" ,
        dropdown: true,
        dropdownLinks: [
            { 
                href: "/edition", 
                label: "Cadastrar nova edição" 
            },
            {
                href: "/editions",
                label: "Visualizar edições"
            }
        ]
    },
    {
        href: "#",
        label: "Apostas",
        dropdown: true,
        dropdownLinks: [
            { 
                href: "/add-betcard", 
                label: "Nova aposta" 
            },
            { 
                href: "/betcards", 
                label: "Visualizar apostas" 
            }
        ]
     },
    { 
        href: "/luckyclients", 
        label: "Premiações", 
        dropdown: true,
        dropdownLinks: [
            { 
                href: "/edition", 
                label: "Iniciar apuração" 
            },
            { 
                href: "/luckyclients", 
                label: "Visualizar todos ganhadores" 
            }
        ]
    }    
];