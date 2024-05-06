import { useEffect, useState } from "react";
import { get } from "./fetchAPI";

const Footer = () => {
    const [hostname, setHostname] = useState<string>('');

    useEffect(() => {
        get('hostname')
            .then(response => response.text())
            .then(data => setHostname(data));
    }, [])

    return (
        <div id='footer'>
            {hostname}
        </div>
    );
};

export default Footer;