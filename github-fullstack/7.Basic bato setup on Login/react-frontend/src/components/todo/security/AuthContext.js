import { createContext, useContext, useState } from "react";
import { executeBasicAuthenticationService } from "../api/HelloWorlApiService";

//1: Create a Context
export const AuthContext = createContext()

export const useAuth = () => useContext(AuthContext)

//2: Share the created context with other components
export default function AuthProvider({ children }) {

    //3: Put some state in the context
    const [isAuthenticated, setAuthenticated] = useState(false)

    // function login(username, password) {
    //     if(username==='paul' && password==='timothy'){
    //         setAuthenticated(true)
    //         return true            
    //     } else {
    //         setAuthenticated(false)
    //         return false
    //     }        
    // }
    function login(username, password) {

        const baToken = 'Basic ' + window.btoa( username + ":" + password )

        executeBasicAuthenticationService(baToken)
        .then (response => console.log(response))
        .catch (error => console.log(error))

        setAuthenticated(false)      
    }

    function logout() {
        setAuthenticated(false)
    }


    return (
        <AuthContext.Provider value={ {isAuthenticated, login, logout}  }>
            {children}
        </AuthContext.Provider>
    )
} 