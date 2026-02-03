document.addEventListener('DOMContentLoaded', () => {
    const spaceForm = document.getElementById('createSpaceForm');
    const zoneSelect = document.getElementById('idZone');

    if (zoneSelect) {
        fetch('/api/zones')
            .then(response => response.json())
            .then(zones => {
                zoneSelect.innerHTML = '<option value="" disabled selected>Seleccione una zona...</option>';
                zones.forEach(zone => {
                    const option = document.createElement('option');
                    option.value = zone.id;
                    option.textContent = zone.nombre || zone.name
                    zoneSelect.appendChild(option);
                });
            })
            .catch(error => {
                console.error("Error cargando zonas:", error);
                zoneSelect.innerHTML = '<option value="" disabled>Error al cargar zonas</option>';
            });
    }

    if (spaceForm) {
        spaceForm.addEventListener('submit', async (e) => {
            e.preventDefault();

            const codigoVal = document.getElementById('codigo').value;
            const ordenVal = parseInt(document.getElementById('orden').value) || 0;
            const estadoVal = document.getElementById('estadoSpace').value;
            const idZoneVal = document.getElementById('idZone').value;
            const reservadoVal = document.getElementById('reservado').checked;

            const spaceData = {
                codigo: document.getElementById('codigo').value,
                status: document.getElementById('estadoSpace').value,
                isReserved: document.getElementById('reservado').checked,
                priority: parseInt(document.getElementById('orden').value) || 0,
                idZone: document.getElementById('idZone').value
            };

            try {
                const response = await fetch('/api/spaces/', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json'
                    },
                    body: JSON.stringify(spaceData)
                });

                if (response.ok) {
                    alert("¡Espacio registrado!");
                    window.location.href = '/spaces';
                } else {
                    const errorBody = await response.json();
                    console.error("Error del servidor:", errorBody);
                    alert("Error: " + (errorBody.message || "Revisa los campos"));
                }
            } catch (error) {
                alert("Error de conexión");
            }
        });
    }

    //(Nielsen #1: Visibilidad)
    document.querySelectorAll('.btn-toggle').forEach(button => {
        button.addEventListener('click', function() {
            const spaceId = this.getAttribute('data-id');
            toggleStatus(spaceId);
        });
    });

    //(Nielsen #5)
    document.querySelectorAll('.btn-delete-space').forEach(button => {
        button.addEventListener('click', function() {
            const spaceId = this.getAttribute('data-id');
            if (confirm('¿Desea eliminar este espacio permanentemente?')) {
                deleteSpace(spaceId);
            }
        });
    });
});

async function toggleStatus(spaceId) {
    try {
        const response = await fetch(`/api/spaces/${spaceId}/status`, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' }
        });
        if (response.ok) {
            location.reload();
        } else {
            alert("No se pudo cambiar el estado.");
        }
    } catch (error) {
        console.error("Error:", error);
    }
}

async function deleteSpace(id) {
    try {
        const response = await fetch(`/api/spaces/${id}`, { method: 'DELETE' });
        if (response.ok) {
            alert("Espacio eliminado.");
            location.reload();
        } else {
            alert("Error al eliminar el espacio.");
        }
    } catch (error) {
        console.error("Error:", error);
    }
}