import {Link} from 'react-router-dom'
import { usernametobepassed } from './LoginComponent'

function WelcomeComponent() {

    const username = usernametobepassed
    return (
        <div className="WelcomeComponent">
            <h1>Welcome {username}</h1>
            <div>
                Manage your todos - <Link to="/todos">Go here</Link>
            </div>
        </div>
    )
}

export default WelcomeComponent